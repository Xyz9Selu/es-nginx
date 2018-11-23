
node('jnlp-slave-docker') {
    def PROJ_GIT_URL = 'https://gitlab+deploy-token-28026:Kh5CPzaP4GYbYaahbMSp@gitlab.com/johns-etc/es-nginx.git'
    def PROJ_GIT_BRANCH = 'master'

    def PROJ_DIR = 'es-nginx'

    def DOCKER_IMG_TAG_PREFIX = ''
    def LOCAL_IMAGE = 'es-nginx'
    def REMOTE_IMAGE = 'registry.cn-hangzhou.aliyuncs.com/endlessstudio/es-nginx'

    def DOCKER_REGISTRY_CREDENTIALS_ID = 'aliyun-docker-registry-endlessstudio'
    def DOCKER_REGISTRY = 'registry.cn-hangzhou.aliyuncs.com'

    stage('get code') {
        sh String.format('git clone -b %s %s', PROJ_GIT_BRANCH, PROJ_GIT_URL)
        dir(PROJ_DIR) {
            sh 'git submodule sync && git submodule update --init --recursive'
        }
    }


    stage('build image http & push') {
        buildImage(PROJ_DIR, 'http', LOCAL_IMAGE, REMOTE_IMAGE, 'http')
        pushImage(DOCKER_REGISTRY_CREDENTIALS_ID, DOCKER_REGISTRY, LOCAL_IMAGE, REMOTE_IMAGE, 'http')

        buildImage(PROJ_DIR, 'uwsgi', LOCAL_IMAGE, REMOTE_IMAGE, 'uwsgi')
        pushImage(DOCKER_REGISTRY_CREDENTIALS_ID, DOCKER_REGISTRY, LOCAL_IMAGE, REMOTE_IMAGE, 'uwsgi')

        buildImage(PROJ_DIR, 'uwsgi-v2', LOCAL_IMAGE, REMOTE_IMAGE, 'uwsgi-v2')
        pushImage(DOCKER_REGISTRY_CREDENTIALS_ID, DOCKER_REGISTRY, LOCAL_IMAGE, REMOTE_IMAGE, 'uwsgi-v2')
    }
}

def buildImage(projDir, subFolder, localImage, remoteImage, tag) {
    print String.format('building %s:%s', localImage, tag)

    localImage = localImage + ':' + tag
    remoteImage = remoteImage + ':' + tag

    dir(projDir) {
        dir(subFolder){
            sh String.format('docker build -t %s .', localImage)
        }
    }
}

def pushImage(dockerCredentialId, dockerRegistry, localImage, remoteImage, tag) {
    print String.format('pushing %s:%s', remoteImage, tag)

    localImage = localImage + ':' + tag
    remoteImage = remoteImage + ':' + tag

    withCredentials([usernamePassword(credentialsId: dockerCredentialId, 
    usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD')]) {
        sh String.format('docker login --username="${USERNAME}" --password="${PASSWORD}" %s', dockerRegistry)
        sh String.format('docker tag %s %s', localImage, remoteImage)
        sh String.format('docker push %s', remoteImage)
    }
}