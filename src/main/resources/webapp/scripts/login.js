//create js login form from html
let loginForm = document.getElementById("login-form");

//this will be for session check
//window.onload = function(){}

console.log(loginForm)
//when login button submitted
//this is an async/await fetch function bc we need to send a POST request
loginForm.onsubmit = async function(e){
    //prevent page from reloading
    e.preventDefault();

    //get values to login as 'User' from input field
    let username = document.getElementById("username").value 
    let password = document.getElementById("password").value 
    let userRoleId
    let radioVal;

    function radioValue(){
        var elem = document.getElementsByName("btnradio");

        for(i = 0; i < elem.length; i++) {
            if(elem[i].checked)
            radioVal = elem[i].value
        }
    }

    radioValue()

    userRoleId = radioVal;

    /* let employeeBtn = document.getElementById("employeeBtn").value
    console.log(employeeBtn)

    console.log(role) */
    /* employeeBtn.onclick = 
    function employeeClk(){
        userRoleId = 1;
    } */
    /* let managerBtn = document.getElementById("managerBtn").value
    console.log(managerBtn) */
    /* managerBtn.onclick =
    function managerClk(){
        userRoleId = 2;
    } */

    console.log(username, password);

    //now we need to send these values to the backend.
    //our request
    let response = await fetch(`${domain}/api/login`, {
        method: "POST",
        body: JSON.stringify({
            username: username,
            password: password
        })
    })

    //now lets get the response back from backend
    //this should include our 'out.println' where we had our response object
    let responseData = await response.json();
    console.log(responseData)

    if(responseData.success){
        if(responseData.data.userRoleId == 1){
            window.location = `${domain}/employee?id=${responseData.data.id}&last=${responseData.data.lastName}&first=${responseData.data.firstName}`
        }else{
            window.location = `${domain}/manager?id=${responseData.data.id}&last=${responseData.data.lastName}&first=${responseData.data.firstName}`
        }
    }else{
        alert("Invalid username, password, or role.\nPlease try again or register.")
        window.location = `${domain}/login`
    }

}
