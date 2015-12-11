mavenJob('directly') {
    description 'A job which calls readFileFromWorkspace directly'

    postBuildSteps {
        shell readFileFromWorkspace('someFile')
    }
}