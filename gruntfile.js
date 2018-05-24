(function(){
    'use strict';

    module.exports = function(grunt) {
        require('load-grunt-tasks')(grunt);

        var staticDirectory,
            scriptsDirectory,
            stylesDirectory;

        staticDirectory = 'src/main/resources/static/';
        scriptsDirectory = staticDirectory + 'scripts/';
        stylesDirectory = staticDirectory + 'styles/';

        grunt.initConfig({
            pkg: grunt.file.readJSON('package.json'),
            clean: {
                all: [
                    scriptsDirectory,
                    stylesDirectory
                ]
            },
            copy: {
                cssNpmToStyles: {
                    files: [{
                        expand: true,
                        cwd: './node_modules/bootstrap/dist/css',
                        src: 'bootstrap.min.css*',
                        dest: stylesDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/angularjs-slider/dist/',
                        src: 'rzslider.min.css',
                        dest: stylesDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/angular-material/',
                        src: 'angular-material.min.css',
                        dest: stylesDirectory
                    }]
                },
                jsNpmToScripts: {
                    files: [{
                      expand: true,
                      cwd: './node_modules/angular/',
                      src: 'angular.min*',
                      dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/angular-cookies/',
                        src: 'angular-cookies.min*',
                        dest: scriptsDirectory
                    },  {
                      expand: true,
                      cwd: './node_modules/@uirouter/angularjs/release/',
                      src: 'angular-ui-router.min*',
                      dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/bootstrap/dist/js/',
                        src: 'bootstrap.min*',
                        dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/jquery/dist/',
                        src: 'jquery.min*',
                        dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/angularjs-slider/dist/',
                        src: 'rzslider.min.js',
                        dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/ui-bootstrap4/dist/',
                        src: '*.js',
                        dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/angular-animate/',
                        src: 'angular-animate.min.js',
                        dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/angular-aria/',
                        src: 'angular-aria.min.js',
                        dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/angular-messages/',
                        src: 'angular-messages.min.js',
                        dest: scriptsDirectory
                    },  {
                        expand: true,
                        cwd: './node_modules/angular-material/',
                        src: 'angular-material.min.js',
                        dest: scriptsDirectory
                    }]
                }
            }
        });

        grunt.registerTask('default', [
            'dev'
        ]);

        grunt.registerTask('dev', [
            'clean:all',
            'copy:jsNpmToScripts',
            'copy:cssNpmToStyles'
        ]);

    };

})();
