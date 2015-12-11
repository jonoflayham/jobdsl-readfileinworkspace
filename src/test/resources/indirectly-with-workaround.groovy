import SomeHelperClass

mavenJob('indirectly-with-workaround') {
    SomeHelperClass.someHelperMethodWithWorkaroundUsingJobManagement(jm, delegate, 'A job which calls readFileFromWorkspace indirectly with a workaround')
}