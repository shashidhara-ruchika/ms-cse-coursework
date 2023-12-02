//defining a class
var User = /** @class */ (function () {
    function User() {
    }
    User.prototype.getUserName = function () {
        return (this.userName);
    };
    User.prototype.getPassword = function () {
        return (this.password);
    };
    return User;
}());
//inheritance
var Employee = /** @class */ (function () {
    //optional and default parameters
    function Employee(nameOfUser, passwordOfUser, typeOfUser) {
        this.userName = nameOfUser;
        this.password = passwordOfUser;
        this.type = typeOfUser;
    }
    Employee.prototype.getUserName = function () {
        return (this.userName);
    };
    Employee.prototype.getPassword = function () {
        return (this.password);
    };
    return Employee;
}());
var users = new Array(new Employee("vishal", "vishal", "Engineer"), new Employee("admin", "admin", "Admin"));
function validate() {
    var flag = false;
    var empType;
    for (var i = 0; i < users.length; i++) {
        console.log(users[i].type); //use i instead of 0
        var inputValue = document.getElementById('email').value;
        if (users[i].userName == inputValue) {
            flag = true;
            empType = users[i].type;
        }
    }
    if (flag) {
        document.getElementById('message').innerHTML = 'Form Submitted for employee type' + empType;
        document.getElementById('message').className = 'alert alert-info';
    }
    else {
        document.getElementById('message').innerHTML = 'No employee found';
        document.getElementById('message').className = 'alert alert-danger';
    }
}
//# sourceMappingURL=msg.js.map