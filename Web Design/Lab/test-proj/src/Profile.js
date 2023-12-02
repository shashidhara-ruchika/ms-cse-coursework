import React, { useState } from "react";
  
  const Profile = () => {
    const [fName,setfName]=useState("Sherlock");
    const [lName,setlName]=useState("Holmes");
    const [email,setEmail]=useState("sherlockholmes@email.com");
    const [btn,setBtn]= useState("Edit Profile");
    const [isEditing,setisEditing]=useState(false);
  
    function edit(){
      var fname= document.getElementById("firstName");
      var lname = document.getElementById("lastName");
      var email = document.getElementById("email");
      setisEditing(true);
      setBtn("Save Changes")
    }
  
    function submit(){
          var fname= document.getElementById("firstName");
      var lname = document.getElementById("lastName");
      var email = document.getElementById("email");
      if (fname.value === "" || lname.value === "" || email.value === "") {
        alert("Please enter all profile fields");
      } else {
        setfName(fname.value);
        setlName(lname.value);
        setEmail(email.value);
        setBtn("Edit Profile");
        alert("Profile updated successfully");
        setisEditing(false);
      }
    }
    return (
      <div className="w-40 mr-75">
        <div className="card pa-50">
          <h1>User Profile</h1>
          <form>
            <div className="layout-column mb-15">
              <label htmlFor="firstname" className="mb-3">
                First Name
              </label>
              {isEditing &&(<input
                type="text"
                id="firstName"
                placeholder="Enter First Name"
                data-testid="firstNameInput"
                disabled={!isEditing}
              />)}
               {!isEditing &&(<div className="card pa-8" data-testid="firstNameDiv" >
                {fName}
              </div>)}
            </div>
            <div className="layout-column mb-15">
              <label htmlFor="lastname" className="mb-3">
                Last Name
              </label>
              {isEditing &&(<input
                type="text"
                id="lastName"
                placeholder="Enter Last Name"
                data-testid="lastNameInput"
                disabled={!isEditing}
              />)}
              {!isEditing &&(<div className="card pa-8" data-testid="lastNameDiv">  {lName}
  </div>)}
            </div>
            <div className="layout-column mb-30">
              <label htmlFor="email" className="mb-3">
                Email
              </label>
              {isEditing &&(<input
                type="text"
                id="email"
                placeholder="Enter Email"
                data-testid="emailInput"
                disabled={!isEditing}
              />)}
               {!isEditing &&(<div className="card pa-8" data-testid="emailDiv">{email}</div>)}
            </div>
            <div className="layout-row justify-content-end">
            <button
                type="button"
                className="mx-0"
                onClick={isEditing ? submit : edit}
                data-testid="changeButton"
              >
                {isEditing ? "Save Changes" : "Edit Profile"}
              </button>
            </div>
          </form>
        </div>
      </div>
    );
  };
  
  export default Profile;