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
    let reimbPastRes;
    reimbPastRes = await fetch(`${domain}/api/financemgr/reimb`)
    loadTable();

    
    document.getElementById("backHome").onclick = function () {
        window.location = `${domain}/manager?id=${userId}&last=${lastName}&first=${firstName}`; 
    };
    document.getElementById("newEmployee").onclick = function () {
        location.href = `./register/index.html?id=${userId}&last=${lastName}&first=${firstName}`; 
    };


    let welcome = document.getElementById("welcome")
    welcome.innerHTML += `<h4> Welcome ${firstName} ${lastName},</h4>`
    
    /* let radioVal;

    function radioValue(){
        var elem = document.getElementsByName("btnradio");

        for(i = 0; i < elem.length; i++) {
            if(elem[i].checked)
            radioVal = elem[i].value
        }
    }

    radioValue()

    let status = radioVal; */
    
    

    document.getElementById("approvedBtn").onclick = async function () {
        status = 1;
        reimbPastRes = await fetch(`${domain}/api/financemgr/reimb/viewStatus?status=${status}`)
        loadTable()
    };
    document.getElementById("deniedBtn").onclick = async function () {
        status = 0;
        reimbPastRes = await fetch(`${domain}/api/financemgr/reimb/viewStatus?status=${status}`)
        loadTable() 
    };

    /* if(status < 0){
        reimbPastRes = await fetch(`${domain}/api/financemgr/reimb/viewStatus?status=${status}`)
        loadTable();
    }else{
        reimbPastRes = await fetch(`${domain}/api/financemgr/reimb`)
        loadTable();
    } */

    async function loadTable(){
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
            <th width="35px">ID</th>
            <th width="125px">Date Submitted</th>
            <th width="80px">Type</th>
            <th width="225px">Description</th>
            <th width="85px">Amount</th>
            <th width="115px">Author</th>
            <th width="125px">Date Resolved</th>
            <th width="125px">Resolver</th>
            <th width="100px">Status</th>
            <th width="100px"></th>
        </thead>
        `;

        reimbPastData.data.forEach(reimb => {
            var view = "View Details";
            reimbPastContElem.innerHTML += `
                <td width = "35px" class="reimb-id" id="reimb-id${reimb.id}">${reimb.id}</td>

                <td width = "125px" class="reimb-sub" id="reimb-sub${reimb.id}">${reimb.submitTime.slice(0, 19)}</td>

                <td width = "80px" class="reimb-type" id="reimb-type${reimb.id}">${reimb.type}</td>

                <td width="225px" class="reimb-desciption" id="reimb-description${reimb.id}">${reimb.description}</td>

                <td width="85px" class="reimb-amount" id="reimb-amount${reimb.id}">$${reimb.amount}</td>

                <td width="115px" class="reimb-author" id="reimb-author${reimb.id}">${reimb.authorLast}, ${reimb.authorFirst}</td>

                <td width="125px" class="reimb-res" id="reimb-res${reimb.id}">${reimb.resolveTime.slice(0, 19)}</td>

                <td width="125px" class="reimb-resolver" id="reimb-resolver${reimb.id}">${reimb.resolverLast}, ${reimb.resolverFirst}</td>

                <td width="100px" class="reimb-status" id="reimb-status${reimb.id}">${reimb.status}</td>

                <td witdh="100px" padding-right="5px" class="reimb-details" id="reimb-details${reimb.id}"> ${view.link(`../reimbursement/index.html?reimbId=${reimb.id}`)}</td>
   
        `

        })
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


