"use sctrict";


module.exports = function (grunt) {

    
    var gruntConfig = {

        pkg: grunt.file.readJSON('package.json'),

        exec: {
            'install.DatePicker': {
                cmd: 'npm install -l; grunt;',
                cwd: 'DatePicker',
                exitCode: 0
            },
            'install.Keyboard': {
                cmd: 'npm install -l; grunt;',
                cwd: 'Keyboard',
                exitCode: 0
            },
            'install.Loading': {
                cmd: 'npm install -l',
                cwd: 'Loading',
                exitCode: 0
            },
            'build.Loading': {
                cmd: 'grunt',
                cwd: 'Loading',
                exitCode: 0
            },
            'install.WebPageManager': {
                cmd: 'npm install -l;',
                cwd: 'WebPageManager',
                exitCode: 0
            }
        }
        
    };


    grunt.initConfig(gruntConfig);
    grunt.loadNpmTasks('grunt-exec');


    grunt.registerTask(
        "default",
        [
            //"exec:build.DatePicker",
            //"exec:build.Keyboard",
            "exec:install.Loading",
            "exec:build.Loading"

            //"exec:build.WebPageManager"
        ]);


};