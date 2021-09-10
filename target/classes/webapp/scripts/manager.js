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
           window.location = `${domain}/login`
       } 
    }else{
        window.location = `${domain}/login`
    }

    document.getElementById("pastReimb").onclick = function () {
        location.href = `pastReimb.html?id=${userId}&last=${lastName}&first=${firstName}`; 
        /* window.location = `${domain}/employee/newReimb?id=${responseData.data.id}&last=${responseData.data.lastName}&first=${responseData.data.firstName}` */
    };

    document.getElementById("newEmployee").onclick = function () {
        location.href = `./register/index.html?id=${userId}&last=${lastName}&first=${firstName}`; 
    };

    let welcome = document.getElementById("welcome")
    welcome.innerHTML += `<h4>Welcome ${firstName} ${lastName},</h4>`
    populateData();
}

async function populateData(){
    
    
    let reimbRes = await fetch(`${domain}/api/financemgr/reimb/view`)
    //convert promise data to json object
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
        <th width="180px">Date Submitted</th>
        <th width="100px">Type</th>
        <th width="380px">Description</th>
        <th width="100px">Amount</th>
        <th width="150px">Author</th>
        <th width="100px">Status</th>
        <th width="100px"></th>
        <th width="100px"> Approve/Deny</th>
    </thead>
    `;

    reimbData.data.forEach(reimb => {
        var view = "View Details";
        reimbContElem.innerHTML += `
            <td width = "45px" class="reimb-id" id="reimb-id${reimb.id}">${reimb.id}</td>

            <td width = "180px" class="reimb-sub" id="reimb-sub${reimb.id}">${reimb.submitTime.slice(0, 19)}</td>

            <td width = "100px" class="reimb-type" id="reimb-type${reimb.id}">${reimb.type}</td>

            <td width="380px" class="reimb-desciption" id="reimb-description${reimb.id}">${reimb.description}</td>

            <td width="100px" class="reimb-amount" id="reimb-amount${reimb.id}">$${reimb.amount}</td>
            
            <td width="150px" class="reimb-author" id="reimb-author${reimb.id}">${reimb.authorLast}, ${reimb.authorFirst}</td>

            <td width="100px" class="reimb-status" id="reimb-status${reimb.id}">${reimb.status}</td>

            <td witdh="100px" padding-right="5px" class="reimb-details" id="reimb-details${reimb.id}"> ${view.link(`../reimbursement/index.html?reimbId=${reimb.id}`)}</td>

            <td><button class="btn btn-success" id="approve-btn${reimb.id}" value=${reimb.id} onclick="approveFtn(${reimb.id})"> Approve </button></td>

            <td><button class="btn btn-danger" id="deny-btn${reimb.id}" onclick="denyFtn(${reimb.id})"> Deny </button></td>
    
    `

    })
 /*    let radioVal;

    function radioValue(){
        var elem = document.getElementsByName("btnradio");

        for(i = 0; i < elem.length; i++) {
            if(elem[i].checked)
            radioVal = elem[i].value
        }
    }

    radioValue()

    let status = radioVal;
    let reimbPastRes;

    if(status < 0){
        reimbPastRes = await fetch(`${domain}/api/financemgr/reimb/viewStatus?status=${status}`)
    }else{
        reimbPastRes = await fetch(`${domain}/api/financemgr/reimb`)
    }

    
    let reimbPastData = await reimbPastRes.json();

    console.log(reimbPastData)

    let reimbPastContElem = document.getElementById("reimbPast-container")
    reimbPastContElem.innerHTML = `
    <thead class="table-head">
        <th width="45px">ID</th>
        <th width="180px">Date Submitted</th>
        <th width="80px">Type</th>
        <th width="250px">Description</th>
        <th width="90px">Amount</th>
        <th width="180px">Date Resolved</th>
        <th width="150px">Resolver</th>
        <th>Status</th>
    </thead>
    `;

    reimbPastData.data.forEach(reimb => {
        reimbPastContElem.innerHTML += `
            <td width = "45px" class="reimb-id" id="reimb-id${reimb.id}">${reimb.id}</td>

            <td width = "180px" class="reimb-sub" id="reimb-sub${reimb.id}">${reimb.submitTime.slice(0, 19)}</td>

            <td width = "80px" class="reimb-type" id="reimb-type${reimb.id}">${reimb.type}</td>

            <td width="250px" class="reimb-desciption" id="reimb-description${reimb.id}">${reimb.description}</td>

            <td width="90px" class="reimb-amount" id="reimb-amount${reimb.id}">$${reimb.amount}</td>

            <td width="180px" class="reimb-res" id="reimb-res${reimb.id}">${reimb.resolveTime.slice(0, 19)}</td>

            <td width="150px" class="reimb-resolver" id="reimb-resolver${reimb.id}">${reimb.resolverLast}, ${reimb.resolverFirst}</td>

            <td class="reimb-status" id="reimb-status${reimb.id}">${reimb.status}</td>
    
    `

    }) */
}


//approve reimbursements
async function approveFtn(id){
    let reimbId = id;
    let status = 1;
    console.log("approve")
    let approveRes = await fetch(`${domain}/api/financemgr/reimb?reimbId=${reimbId}&userId=${userId}&status=${status}`,{
        method: "PATCH",
        body: JSON.stringify({
            reimbId: reimbId,
            userId: userId,
            status: status
        })
    })

    let approveData = await approveRes.json();

    if(approveData.success){
        populateData()
    }

}


//delete item given id
async function denyFtn(id){
    let reimbId = id;
    let status = 0;
    console.log("deny")
    let denyRes = await fetch(`${domain}/api/financemgr/reimb?reimbId=${reimbId}&userId=${userId}&status=${status}`,{
        method: "PATCH",
        body: JSON.stringify({
            reimbId: reimbId,
            userId: userId,
            status: status
        })
    })

    let denyData = await denyRes.json();

    if(denyData.success){
        populateData()
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

