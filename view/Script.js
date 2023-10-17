//arquivo que fez a conexão com  o back-end da api]

//URL QUE TEM OS DADOS QUE PRECISAMOS
const url = "http://localhost:8080/task/user/1";

//Função que esconde o loading do HTML
function hideLoader(){
    document.getElementById("loading").style.display="none";
}

//Função que realização a mostrangem das informações da API no FRONT END
function show(tasks){
    
    //Variavel com a criação do cabeçalho da tabela
    let tab = `<thead>
                <th scope="col">#</th>
                <th scope="col">Description</th>
                <th scope="col">Username</th>
                <th scope="col">User Id</th>
                </thead>`;

    //Loop com os resultado trago de dentro da api
    for (let task of tasks) {
        tab += `
            <tr>
                <td scope="row">${task.id}</td>
                <td >${task.description}</td>
                <td >${task.user.userName}</td>
                <td >${task.user.id}</td>
            </tr>
        `;
    } 
    //Google bard ajudou a me explicar oque faz a linha abaixo
      //A linha de código document.getElementById("tasks").innerHTML = tab; é usada em JavaScript para alterar o conteúdo HTML de um elemento com o id tasks
    document.getElementById("tasks").innerHTML = tab;
}

//Função assincrona que recebe os dados da api
async function getAPI(url){
    const response = await fetch(url, {method: "GET"});
    var data = await response.json();
    console.log(data);
    if(response){
        hideLoader();
        show(data);
    }else{

    }
}
//Chamando e usando função
getAPI(url)