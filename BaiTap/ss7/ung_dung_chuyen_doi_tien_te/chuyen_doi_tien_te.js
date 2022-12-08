function ChangeMoney() {
    let amount = document.getElementById("amount").value;
    let formC = document.getElementById("from").value;
    let to = document.getElementById("to").value;
    let result='';
    if (formC == "USD" && to == "VND") {
        result = "result: " + (amount * 23000) + " Đ";
    }
    else if (formC == "VND"&& To =="USD"){
        result = "result: "+ (amount / 23000) + " $";
    }
    else if (formC == "VND") {
        result = "result: " + amount + " Đ";
    } else {
        result = "result: " + amount + " $";
    }
    document.getElementById("result").innerHTML = result;
}