class SomeHelperClass {
    def static someHelperMethod(ctx, descriptionText) {
        ctx.description(descriptionText)

        ctx.postBuildSteps {
            // This doesn't work:
            shell readFileInWorkspace('someFile')

            // Nor does this:
            shell ctx.readFileInWorkspace('someFile')
        }
    }

    def static someHelperMethodWithWorkaroundUsingJobManagement(jm, ctx, descriptionText) {
        ctx.description(descriptionText)

        ctx.postBuildSteps {
            shell jm.readFileInWorkspace('someFile')
        }
    }
