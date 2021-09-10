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

    document.getElementById("newReimb").onclick = function () {
        location.href = `newReimb.html?id=${userId}&last=${lastName}&first=${firstName}`; 
        /* window.location = `${domain}/employee/newReimb?id=${responseData.data.id}&last=${responseData.data.lastName}&first=${responseData.data.firstName}` */
    };

    let welcome = document.getElementById("welcome")
    welcome.innerHTML += `<h4> Welcome ${firstName} ${lastName},</h4>`
    

    populateData();
}

async function populateData(){
    
    let reimbRes = await fetch(`${domain}/api/employee/reimb?userId=${userId}`)
    let reimbData = await reimbRes.json();

    //sort the data
    reimbData.data.sort((a,b) => {
        if(a.submitTime < b.submitTime)
            return -1
        if(a.submitTime > b.submitTime)
            return 1;
        
        return 0;    
    })

    console.log(reimbData)

    let reimbContElem = document.getElementById("reimb-container")
    reimbContElem.innerHTML = `
    <thead class="table-head">
        <th width="45px">ID</th>
        <th width="210px">Date Submitted</th>
        <th width="100px">Type</th>
        <th width="525px">Description</th>
        <th width="130px">Amount</th>
        <th width="130px">Receipt</th>
        <th width="100px">Status</th>
        <th width="100px"></th>
    </thead>
    `;


    reimbData.data.forEach(reimb => {
        var view = "View Details";
        var receiptProv = false
        if(!reimb.receipt == null){
            receiptProv = true
        }
        reimbContElem.innerHTML += `
            <td width = "45px" class="reimb-id" id="reimb-id${reimb.id}">${reimb.id}</td>

            <td width = "210px" class="reimb-sub" id="reimb-sub${reimb.id}">${reimb.submitTime.slice(0, 19)}</td>

            <td width = "100px" class="reimb-type" id="reimb-type${reimb.id}">${reimb.type}</td>

            <td width="525px" class="reimb-desciption" id="reimb-description${reimb.id}">${reimb.description}</td>

            <td width="130px" class="reimb-amount" id="reimb-amount${reimb.id}">$${reimb.amount}</td>

            <td width="130px" class="reimb-receipt" id="reimb-receipt${reimb.id}">${receiptProv}</td>

            <td width="100px" class="reimb-status" id="reimb-status${reimb.id}">${reimb.status}</td>

            <td witdh="100px" padding-right="5px" class="reimb-details" id="reimb-details${reimb.id}"> ${view.link(`../reimbursement/index.html?reimbId=${reimb.id}`)}</td>
    `

    })

    let reimbPastRes = await fetch(`${domain}/api/employee/reimb/views?userId=${userId}`)
    let reimbPastData = await reimbPastRes.json();

    //sort the data
    reimbPastData.data.sort((a,b) => {
        if(a.submitTime < b.submitTime)
            return -1
        if(a.submitTime > b.submitTime)
            return 1;
        
        return 0;    
    })

    console.log(reimbPastData)

    let reimbPastContElem = document.getElementById("reimbPast-container")
    reimbPastContElem.innerHTML = `
    <thead class="table-head">
        <th width="45px">ID</th>
        <th width="180px">Date Submitted</th>
        <th width="100px">Type</th>
        <th width="340px">Description</th>
        <th width="100px">Amount</th>
        <th width="180px">Date Resolved</th>
        <th width="180px">Resolver</th>
        <th width="110px">Status</th>
        <th width="100px"></th>
    </thead>
    `;

    reimbPastData.data.forEach(reimb => {
        var view = "View Details";
        reimbPastContElem.innerHTML += `
            <td width = "45px" class="reimb-id" id="reimb-id${reimb.id}">${reimb.id}</td>

            <td width = "180px" class="reimb-sub" id="reimb-sub${reimb.id}">${reimb.submitTime.slice(0, 19)}</td>

            <td width = "100px" class="reimb-type" id="reimb-type${reimb.id}">${reimb.type}</td>

            <td width="340px" class="reimb-desciption" id="reimb-description${reimb.id}">${reimb.description}</td>

            <td width="100px" class="reimb-amount" id="reimb-amount${reimb.id}">$${reimb.amount}</td>

            <td width="180px" class="reimb-res" id="reimb-res${reimb.id}">${reimb.resolveTime.slice(0, 19)}</td>

            <td width="180px" class="reimb-resolver" id="reimb-resolver${reimb.id}">${reimb.resolverLast}, ${reimb.resolverFirst}</td>

            <td width="110px" class="reimb-status" id="reimb-status${reimb.id}">${reimb.status}</td>
    
            <td witdh="100px padding-right="5px" class="reimb-details" id="reimb-details${reimb.id}"> ${view.link(`../reimbursement/index.html?reimbId=${reimb.id}`)}</td>
 
    `

    })
}
//end session and redirect to login when logout button is clicked
let logoutBtn = document.getElementById("logout-btn")
logoutBtn.onclick = async function(){
    let logoutRes = await fetch(`${domain}/api/logout`)

    let logoutResData = await logoutRes.json();

    if(logoutResData.success)
        window.location = `${domain}/login`
}
/*
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
        populateData();
    }
} */