



//defining a class
class User {
    userName:string;
    password:string;

    getUserName() {
        return (this.userName);
    }
    
      getPassword() {
        return (this.password);
    }
}

//inheritance
class Employee {   
    type:string;
     userName:string;
    password:string;

    getUserName() {
        return (this.userName);
    }
    
      getPassword() {
        return (this.password);
    }
    //optional and default parameters
    constructor (nameOfUser,passwordOfUser,typeOfUser) {
     this.userName=nameOfUser;
     this.password=passwordOfUser;
     this.type=typeOfUser;
       
    }




}

var users: Employee[] = new Array(new Employee("vishal", "vishal","Engineer"),new Employee("admin", "admin","Admin"));



function validate()
{

var flag=false;
var empType;
for(let i=0; i<users.length; i++){
    console.log(users[i].type); //use i instead of 0
var inputValue = (<HTMLInputElement>document.getElementById('email')).value;
    if(users[i].userName==inputValue)
    {
    flag=true;
    empType=users[i].type;
    
    }
}

if(flag)
{
	document.getElementById('message').innerHTML='Form Submitted for employee type'+empType;
    document.getElementById('message').className='alert alert-info';
}
else
{
document.getElementById('message').innerHTML='No employee found';
    document.getElementById('message').className='alert alert-danger';
	
}

}



