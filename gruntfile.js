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
                    }]
                },
                jsNpmToScripts: {
                    files: [{
                      expand: true,
                      cwd: './node_modules/angular/',
                      src: 'angular.min*',
                      dest: scriptsDirectory
                    }, {
                        expand: true,
                        cwd: './node_modules/angular-cookies/',
                        src: 'angular-cookies.min*',
                        dest: scriptsDirectory
                    }, {
                      expand: true,
                      cwd: './node_modules/@uirouter/angularjs/release/',
                      src: 'angular-ui-router.min*',
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
