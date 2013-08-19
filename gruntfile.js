"use sctrict";


module.exports = function (grunt) {

    
    var gruntConfig = {

        pkg: grunt.file.readJSON('package.json'),

        exec: {
            'integration.tests': {
                cmd: 'cordova build -d',
                cwd: 'specs/integration.tests/',
                exitCode: 0
            }
        }
        
    };


    grunt.initConfig(gruntConfig);
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-exec');


    grunt.registerTask(
        "default",
        [
            
        ]);


    grunt.registerTask(
        "test",
        [
            'exec:integration.tests' 
        ]);

};