import SomeHelperClass

mavenJob('indirectly') {
    SomeHelperClass.someHelperMethod(delegate, 'A job which calls readFileFromWorkspace indirectly, and which sadly does not work')
}