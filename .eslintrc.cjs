/** @type { import("eslint").Linter.Config } */
module.exports = {
	root: true,
	extends: [
		'eslint:recommended',
		'plugin:@typescript-eslint/recommended',
		'plugin:svelte/recommended'
	],
	parser: '@typescript-eslint/parser',
	plugins: ['@typescript-eslint', '@stylistic'],
	parserOptions: {
		sourceType: 'module',
		ecmaVersion: 2020,
		extraFileExtensions: ['.svelte']
	},
	env: {
		browser: true,
		es2017: true,
		node: true
	},
    rules: {
        '@stylistic/indent': ['error', 4],
        '@stylistic/semi': ['error', 'always'],
        '@stylistic/quotes': ['error', 'double'],
        '@stylistic/no-mixed-spaces-and-tabs': 'error',
    },
	overrides: [
		{
			files: ['*.svelte'],
			parser: 'svelte-eslint-parser',
			parserOptions: {
				parser: '@typescript-eslint/parser'
			},
            rules: {
                'svelte/indent': ['error', { 'indent': 2 }],
                '@stylistic/indent': 'off',
            }
		}
	],
};
