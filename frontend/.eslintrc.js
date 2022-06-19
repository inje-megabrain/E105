module.exports = {
  env: {
    browser: true,
    commonjs: true,
    amd: true,
    node: true,
  },
  extends: [
    'eslint:recommended',
    'airbnb',
    'plugin:prettier/recommended',
    'react-app',
    'react-app/jest',
    'plugin:import/typescript',],
  rules: {
    'react/jsx-filename-extension': ['error', { extensions: ['.js', '.jsx'] }],
  },
  settings: {
    'import/resolver': {
      node: {
        "paths": ["src"],
        extensions: ['.js', '.jsx', '.ts', '.tsx'],
      },
      'typescript': {},
    },
  },
};
