

module.exports = {

    // run all testings using requirejs loading

    tests: {

        options: {
                
                specs: [
                    'spec/*Spec.js'
                ],
                
                outfile: '.tests.html',
                
                // use custom template to fix test describing templating
                template: require('grunt-template-jasmine-requirejs'),

                start: "spec/lib/start",

                templateOptions: {
                    requireConfig:{

                        baseUrl: 'src/',

                        paths: {
                            'squire': "../../spec/lib/squire-latest",
                            'jasmine.async': "../../spec/lib/jasmine.async-latest"
                        },

                        shim: {
                            'jasmine.async': {
                                exports: 'AsyncSpec'
                            }
                        }

                    }
                },

                vendor: [
                    // jasmine async
                   "spec/lib/jasmine.async-latest.js"
                ],


                keepRunner: true

            }

    }

    
};