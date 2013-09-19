"use sctrict";


module.exports = function (grunt) {

    
    var gruntConfig = {

        pkg: grunt.file.readJSON('package.json'),

        exec: {


            'install._PluginTemplate': {
                cmd: 'npm install -l',
                cwd: '_PluginTemplate',
                exitCode: 0
            },
            'build._PluginTemplate': {
                cmd: 'grunt',
                cwd: '_PluginTemplate',
                exitCode: 0
            },

            
            'install.DatePicker': {
                cmd: 'npm install -l',
                cwd: 'DatePicker',
                exitCode: 0
            },
            'build.DatePicker': {
                cmd: 'grunt',
                cwd: 'DatePicker',
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


            'install.Keyboard': {
                cmd: 'npm install -l',
                cwd: 'Keyboard',
                exitCode: 0
            },
            'build.Keyboard': {
                cmd: 'grunt',
                cwd: 'Keyboard',
                exitCode: 0
            },


            'install.WebPageManager': {
                cmd: 'npm install -l',
                cwd: 'WebPageManager',
                exitCode: 0
            },
            'build.WebPageManager': {
                cmd: 'grunt',
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

            "exec:install._PluginTemplate",
            "exec:build._PluginTemplate",

            "exec:install.DatePicker",
            "exec:build.DatePicker",

            "exec:install.Loading",
            "exec:build.Loading",

            "exec:install.Keyboard",
            "exec:build.Keyboard",

            "exec:install.WebPageManager",
            "exec:build.WebPageManager"
            
        ]);


};