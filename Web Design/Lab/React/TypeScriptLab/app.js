define(["require", "exports", "greeter"], function (require, exports, model) {
    "use strict";
    Object.defineProperty(exports, "__esModule", { value: true });
    var el = document.getElementById('content');
    var greeter = new model.Greeter(el);
    greeter.start();
});
//# sourceMappingURL=app.js.map