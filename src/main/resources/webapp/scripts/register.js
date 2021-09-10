const urlParams = new URLSearchParams(window.location.search)
const userId = urlParams.get("id")
const firstName = urlParams.get("first")
const lastName = urlParams.get("last")

let registerForm = document.getElementById("register-form");
window.onload = async function(){
document.getElementById("backHome").onclick = function () {
    window.location = `${domain}/manager?id=${userId}&last=${lastName}&first=${firstName}`; 
};
}

registerForm.onsubmit = async function(e){
    e.preventDefault();

    //get values from the input field
    let username = document.getElementById("username").value;
    let pass = document.getElementById("password").value;
    let confirm = document.getElementById("confPassword").value;
    let password;
    let firstName = document.getElementById("firstName").value;
    let lastName = document.getElementById("lastName").value;
    let email = document.getElementById("email").value;
    let userRoleId;

    if(!pass === confirm){
        alert("Passwords must match\nPlease try again.")
    }else{
        password = pass;
    }
    function radioValue(){
        var elem = document.getElementsByName("btnradio");

        for(i = 0; i < elem.length; i++) {
            if(elem[i].checked)
            userRoleId = elem[i].value
        }
    }

    radioValue()


    console.log("Welcome" + username)

    //how do we send values to the backend?
    let response = await fetch(`${domain}/api/register`,{
        method: "POST",
        body: JSON.stringify({
            username: username,
            password: password,
            firstName: firstName,
            lastName: lastName,
            email: email,
            userRoleId: userRoleId
        })
    })

    let responseData = await response.json();
    console.log(responseData)

    if(responseData.success){
        alert("Account Created!")
    }else{
        alert("This username already exists. \n Please try a different username. \n If error occurs again try another email.")
    }


}