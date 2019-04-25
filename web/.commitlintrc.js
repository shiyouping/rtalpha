module.exports = {
  parserPreset: {
    parserOpts: {
      issuePrefixes: []
    }
  },
  rules: {
    "references-empty": [2, "never"],
    "type-empty": [2, "never"],
    "type-enum": [2, "always", [
      'build',
      'ci',
      'chore',
      'docs',
      'feat',
      'fix',
      'perf',
      'refactor',
      'revert',
      'style',
      'test'
    ]],
    "subject-empty": [2, "never"]
  }
};