

module.exports = {

    // run all testings using requirejs loading

    tests: {

        options: {
                
                specs: [
                    'www/*Spec.js'
                ],
                
                outfile: '.tests.html',
                
                // use custom template to fix test describing templating
                template: require('grunt-template-jasmine-requirejs'),

                keepRunner: true,


                templateOptions: {
                  requireConfigFile: '.require.js'
                },

                vendor: [

                    // jasmine async
                    "spec/lib/jasmine.async-latest.js"

                ]

            }

    }

    
};