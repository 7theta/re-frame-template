const defaultTheme = require('tailwindcss/defaultTheme');
const colors = require('tailwindcss/colors');

module.exports = {
    purge: [
        './resources/public/**/*.html',
        './resources/public/**/*.js'
    ],
    theme: {
        extend: {
            fontFamily: {
                sans: ['Inter var', ...defaultTheme.fontFamily.sans]
            },
        },
        colors: colors
    },
    plugins: [
        require('@tailwindcss/forms')
    ]
};
