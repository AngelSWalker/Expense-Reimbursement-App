const urlParams = new URLSearchParams(window.location.search)
const userId = urlParams.get("id")
const firstName = urlParams.get("first")
const lastName = urlParams.get("last")

window.onload = async function(){
    //check session
    const sessionRes = await fetch(`${domain}/api/check-session`)
    const sessionUserData = await sessionRes.json()

    console.log(sessionUserData)
    if(sessionUserData.data){
       if(sessionUserData.data.id != userId){
           window.location = `'${domain}/login`
       } 
    }else{
        window.location = `${domain}/login`
    }

    let welcome = document.getElementById("welcome")
    welcome.innerHTML += `<h4> Welcome ${firstName} ${lastName},</h4>`
    

}

document.getElementById("backHome").onclick = function () {
    window.location = `${domain}/employee?id=${userId}&last=${lastName}&first=${firstName}`; 
};

let createReimbForm = document.getElementById("create-reimbursement-form")

createReimbForm.onsubmit = async function(e){
    e.preventDefault();

    let reimbAmountVal = document.getElementById("amount").value;

    let reimbDescription = document.getElementById("description").value;

    let typeVal;

    function radioValue(){
        var elem = document.getElementsByName("btnradio");

        for(i = 0; i < elem.length; i++) {
            if(elem[i].checked)
            typeVal = elem[i].value
        }
    }

    radioValue()
    if(typeVal === undefined){
        alert("A type is required. \nPlease select one.")
    }
    console.log(typeVal)

    let createRes = await fetch(`${domain}/api/employee/reimb`, {
        method: "POST",
        body: JSON.stringify({
            amount: reimbAmountVal,
            description: reimbDescription,
            authorId: userId,
            typeId: typeVal
        })
    })

    let createResData = await createRes.json()

    if(createResData.success){
        let amountInputElem = document.getElementById("amount")
        amountInputElem.value = ''
        let desciptionInputElem = document.getElementById("description")
        desciptionInputElem.value = ''
        if(confirm("Reimbursement created successfully!\nTo view your reimbursements press OK.\nTo submit another press CANCEL")){
            window.location = `${domain}/employee?id=${userId}&last=${lastName}&first=${firstName}`
        }else{
            window.location = `${domain}/employee/newReimb.html?id=${userId}&last=${lastName}&first=${firstName}`
        }
    }else{
        alert("Somethings wrong. \nPlease try again.")
        window.location = `${domain}/employee/newReimb.html?id=${userId}&last=${lastName}&first=${firstName}`
    }
}

//end session and redirect to login when logout button is clicked
let logoutBtn = document.getElementById("logout-btn")
logoutBtn.onclick = async function(){
    let logoutRes = await fetch(`${domain}/api/logout`)

    let logoutResData = await logoutRes.json();

    if(logoutResData.success)
        window.location = `${domain}/login`
}