"use sctrict";


module.exports = function (grunt) {

    
    var gruntConfig = {

        pkg: grunt.file.readJSON('package.json'),

        exec: require("./.grunt-tasks/exec.js"),
        jshint: require("./.grunt-tasks/jshint.js"),
        jasmine: require("./.grunt-tasks/jasmine.js"),

        'http-server': {

            'root': {
                root: "",
                port: 8585//,
                //runInBAckground: true
            }

         }
        
    };


    grunt.initConfig(gruntConfig);
    grunt.loadNpmTasks('grunt-contrib-jshint');
    grunt.loadNpmTasks('grunt-exec');
    grunt.loadNpmTasks('grunt-contrib-jasmine');
    grunt.loadNpmTasks('grunt-http-server');

    grunt.registerTask(
        "default",
        [
            "jshint",
            "jasmine"
        ]);


    grunt.registerTask(
        "dev",
        [
            "http-server"
        ]);

};