function ChangeMoney() {
    let amount = document.getElementById("amount").value;
    let fromC = document.getElementById("from").value;
    let to = document.getElementById("to").value;
    let result='';
    if (fromC == "USD" && to == "VND") {
        result = "result: " + (amount * 23000) + " Đ";
    }
    else if (fromC == "VND"&& To =="USD"){
        result = "result: "+ (amount / 23000) + " $";
    }
    else if (fromC == "VND") {
        result = "result: " + amount + " Đ";
    } else {
        result = "result: " + amount + " $";
    }
    document.getElementById("result").innerHTML = result;
}