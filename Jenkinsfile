pipeline {
    agent {  label 'infra' }
    stages {
        stage('Clone') {
            steps {
                git branch: 'develop', url: "https://github.com/veronica-platform/veronica-common.git"
            }
        }
        stage('Artifactory configuration') {
            steps {
                rtMavenDeployer(
                    id: 'deployer',
                    serverId: 'rp-artifactory-server',
                    releaseRepo: 'libs-release-local',
                    snapshotRepo: 'libs-snapshot-local'
                )
                rtMavenResolver(
                    id: 'resolver',
                    serverId: 'rp-artifactory-server',
                    releaseRepo: 'libs-release',
                    snapshotRepo: 'libs-snapshot'
                )
            }
        }
        stage('Exec Maven') {
            steps {
                rtMavenRun (
                    tool: 'MAVEN_HOME',
                    pom: 'pom.xml',
                    goals: 'clean install',
                    deployerId: 'deployer',
                    resolverId: 'resolver'
                )
            }
        }
        stage('Publish build info') {
            steps {
                rtPublishBuildInfo (
                    serverId: 'rp-artifactory-server'
                )
                rtAddInteractivePromotion (
                    serverId: 'rp-artifactory-server',
                    buildName: JOB_NAME,
                    buildNumber: BUILD_NUMBER
                )
            }
        }
    }

    post {
        always {
            cleanWs()
        }
    }
}