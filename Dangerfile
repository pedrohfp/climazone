# Ignore bot reviews
declared_trivial = (github.pr_title + github.pr_body).include?("#trivial")

# Tests
Dir["**/test-results/testDebugUnitTest/*.xml"].each do |file_name|
  junit.parse file_name
  junit.report
end

# Detekt
Dir["**/reports/detekt/detekt.xml"].each do |file_name|
  checkstyle_format.report(file_name, inline_mode = true)
end

# Ktlint
checkstyle_format.base_path = Dir.pwd
Dir["**/reports/ktlint/ktlintMainSourceSetCheck/**.txt"].each do |file_name|
  checkstyle_format.report(file_name, inline_mode = true)
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