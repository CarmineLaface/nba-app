
val ktlint by configurations.creating

dependencies {
    ktlint("com.pinterest:ktlint:0.43.2") {
        attributes {
            attribute(Bundling.BUNDLING_ATTRIBUTE, objects.named(Bundling.EXTERNAL))
        }
    }
}

val outputDir = "${project.buildDir}/reports/ktlint/"

val ktlintCheck by tasks.creating(JavaExec::class) {
    val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Check Kotlin code style."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("src/**/*.kt")
}

val ktlintFormat by tasks.creating(JavaExec::class) {
    val inputFiles = project.fileTree(mapOf("dir" to "src", "include" to "**/*.kt"))
    inputs.files(inputFiles)
    outputs.dir(outputDir)

    description = "Fix Kotlin code style deviations."
    classpath = ktlint
    main = "com.pinterest.ktlint.Main"
    args = listOf("-F", "src/**/*.kt")
}