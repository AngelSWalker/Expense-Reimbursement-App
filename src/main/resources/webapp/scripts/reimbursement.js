/* $('#myModal').on('shown.bs.modal', function () {
    $('#myInput').trigger('focus')
}) */
/* function called(){
    jQuery.noConflict();
    $('#myModal').modal();
    console.log("modal clicked")
    
} */
/* 
var myModal = document.getElementById('myModal')
var myInput = document.getElementById('myInput')

myModal.addEventListener('shown.bs.modal', function () {
  myInput.focus()
}) */

const urlParams = new URLSearchParams(window.location.search)
const reimbId = urlParams.get("reimbId")

window.onload = async function(){

  let detailRes = await fetch(`${domain}/api/reimbursement?reimbId=${reimbId}`)
  let detailData = await detailRes.json();

  console.log(detailData)
  let resolve;
  let time;

  if(detailData.data.resolveTime == null){
    resolve = "Not resolved just yet";
    time = "Read previous :)"
  }else{
    resolve = `${detailData.data.resolverFirst}, ${detailData.data.resolverFirst}`
    time = `${detailData.data.resolveTime.slice(0, 19)}`
  }

  let detailContElem = document.getElementById("reimb-container")
    detailContElem.innerHTML += `
      <div id="reimb-cont">
        <div class="reimb-id" id="reimb-id${detailData.data.id}">
          <h2 >${detailData.data.id}</h2>
        </div>

        <div class="reimb-amount" id="reimb-amount${detailData.data.id}">
          <h4>${detailData.data.amount}</h4>
        </div>

        <div class="reimb-type" id="reimb-type${detailData.data.id}">
          <h4>${detailData.data.type}</h4>
        </div>

        <div class="reimb-description" id="reimb-description${detailData.data.id}">
          <h5><b>Description:</b></h5>
          <h6 id="description-txt">${detailData.data.description}</h6>
        </div>

        <div class="receipt-div" id="receipt-div${detailData.data.id}">
          <button id="receipt-btn">${detailData.data.receipt}</button>
        </div>

        <div class="reimb-author" id="reimb-author${detailData.data.id}">
          <h5><b>Submitted By: </b>${detailData.data.authorFirst}, ${detailData.data.authorLast}</h5>
          <h6><i>on: ${detailData.data.submitTime.slice(0, 19)}</i></h6>
        </div>

        <div class="reimb-resolver" id="reimb-resolver${detailData.data.id}">
          <h5><b>Resolved By: </b>${resolve}</h5>
          <h6><i>on: ${time}</i></h6>
        </div>

        <div class="reimb-status" id="reimb-status${detailData.data.id}">
          <h5>${detailData.data.status}</h5>
      </div>
  `
  
}