
describe("LoadingSpec", function () {

    var Squire = null,
        Injector = null,
        async = new AsyncSpec(this);


    async.beforeEach(function (done) {

        require(["require", "squire", "cordova/exec"], function(require){
            
            Squire = require("squire");
            Injector = new Squire();
            
            done();

        });

    });


    async.it("module should be loaded", function (done) {

        
        require(["SharePlugin"], function(PLUGIN){

            expect(!!PLUGIN).toBeTruthy();
            expect(!!PLUGIN.share).toBeTruthy();

            done();

        });

    });
    


});
