# Ignore bot reviews
declared_trivial = (github.pr_title + github.pr_body).include?("#trivial")

#Detekt
kotlin_detekt.severity = "warning"
kotlin_detekt.skip_gradle_task = true
kotlin_detekt.gradle_task = "detektAll"
kotlin_detekt.report_file = "build/reports/detekt/detekt.xml"
kotlin_detekt.detekt(inline_mode: true)

#Tests
junit_tests_dir = "**/test-results/**/*.xml"
Dir[junit_tests_dir].each do |file_name|
  junit.parse file_name
  junit.report
end

#Necessary Unit Tests
diff = (git.added_files + git.modified_files).select { |item| !item.start_with?(".danger") }
test_changes = diff.include?("**/src/test/**")
is_only_dependencies_change = diff.all? { |s| s.start_with?("buildSrc") }

if !test_changes && !declared_trivial && diff.include?("*.kt") && !is_only_dependencies_change
   fail("Por favor escreva testes unitários para este PR")
end

#Necessary Android Tests
diff = (git.added_files + git.modified_files).select { |item| !item.start_with?(".danger") }
test_changes = diff.include?("**/src/androidTest/**")
is_only_dependencies_change = diff.all? { |s| s.start_with?("buildSrc") }

if !test_changes && !declared_trivial && diff.include?("*.kt") && !is_only_dependencies_change
   fail("Por favor escreva testes de UI para este PR")
end