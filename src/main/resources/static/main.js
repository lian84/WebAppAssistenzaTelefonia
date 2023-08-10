<!-- Script per caricare la tabella clienti -->
function caricaTabellaClienti() {
  $.get("/api/clienti", function(data) {
    var tabellaHtml = "<table class='table table-striped table-bordered table-hover'>";
    tabellaHtml += "<thead><tr><th>Cod.Cliente</th><th>Nome e Cognome</th><th>Indirizzo</th><th>Dettagli</th><th>Articoli in Assistenza</th></tr></thead>";
    tabellaHtml += "<tbody>";

    for (var i = 0; i < data.length; i++) {
      var cliente = data[i];
      tabellaHtml += "<tr>";
      tabellaHtml += "<td>" + cliente.id + "</td>";
      tabellaHtml += "<td>" + cliente.nome + " " + cliente.cognome + "</td>";
      tabellaHtml += "<td>" + cliente.indirizzo + ", " + cliente.comune + ", " + cliente.cap + ", " + cliente.provincia + "</td>";
      tabellaHtml += "<td><button onclick='mostraDettagliCliente(" + cliente.id + ")' class='btn btn-primary'>Dettagli</button></td>";
      tabellaHtml += "<td><button onclick='caricaArticoliAssistenza(" + cliente.id + ")' class='btn btn-primary'>Articoli in Assistenza</button></td>";
      tabellaHtml += "</tr>";
    }

    tabellaHtml += "</tbody></table>";
    $("#tabellaContainer").html(tabellaHtml);
  });
}
<!-- Script per mostrare gli articoli in assistenza del cliente -->
function caricaArticoliAssistenza(clienteId) {
  $.get("/api/clienti/" + clienteId + "/assistenza", function(data) {
    // Chiamata separata per ottenere il nome del cliente
    $.get("/api/clienti/" + clienteId, function(cliente) {
      var tableHtml = "<h3>Articoli in Assistenza per il cliente: " + cliente.nome + " " + cliente.cognome +"</h3>";
      if (data && data.length > 0) {
        tableHtml += "<table class='table table-striped table-bordered table-hover'>";
        tableHtml += "<tr><th>Marca</th><th>Modello</th><th>Tipo Guasto</th><th>Assistenza</th><th>Stato Assistenza</th></tr>";
        for (var i = 0; i < data.length; i++) {
          var articolo = data[i];
          tableHtml += "<tr>";
          tableHtml += "<td>" + articolo.marca + "</td>";
          tableHtml += "<td>" + articolo.modello + "</td>";
          tableHtml += "<td>" + articolo.tipo_guasto + "</td>";
          tableHtml += "<td>" + (articolo.assistenza ? articolo.assistenza.nome : '') + "</td>";
          tableHtml += "<td>" + (articolo.stato ? articolo.stato.stato : '') + "</td>";
          tableHtml += "</tr>";
        }
        tableHtml += "</table>";
      } else {
        tableHtml += "<p>Nessun articolo in assistenza per questo cliente.</p>";
      }
      $("#tabellaContainer").html(tableHtml);
    });
  }).fail(function() {
    $("#tabellaContainer").html("<p>Errore durante il recupero degli articoli in assistenza.</p>");
  }).always(function() {
    $("#loader").hide();
  });
}



<!-- Script per modificare i dettagli clienti -->
function modificaCliente(clienteId, cliente) {
  // Costruisce la tabella dei dettagli del cliente con campi di input modificabili
  var dettagliHtml = "<table class='table table-striped'>";
  dettagliHtml += "<tr><td><strong>Id:</strong></td><td>" + cliente.id + "</td></tr>";
  dettagliHtml += "<tr><td><strong>Nome:</strong></td><td><input type='text' id='nomeInput' value='" + cliente.nome + "'></td></tr>";
  dettagliHtml += "<tr><td><strong>Cognome:</strong></td><td><input type='text' id='cognomeInput' value='" + cliente.cognome + "'></td></tr>";
  dettagliHtml += "<tr><td><strong>Via:</strong></td><td><input type='text' id='indirizzoInput' value='" + cliente.indirizzo + "'></td></tr>";
  dettagliHtml += "<tr><td><strong>CAP:</strong></td><td><input type='text' id='capInput' value='" + cliente.cap + "'></td></tr>";
  dettagliHtml += "<tr><td><strong>Comune:</strong></td><td><input type='text' id='comuneInput' value='" + cliente.comune + "'></td></tr>";
  dettagliHtml += "<tr><td><strong>Provincia:</strong></td><td><input type='text' id='provinciaInput' value='" + cliente.provincia + "'></td></tr>";
  dettagliHtml += "<tr><td><strong>Telefono:</strong></td><td><input type='text' id='telefonoInput' value='" + cliente.tel + "'></td></tr>";
  dettagliHtml += "<tr><td><strong>E-Mail:</strong></td><td><input type='text' id='emailInput' value='" + cliente.mail + "'></td></tr>";

  // Aggiunge il pulsante di salvataggio dei dati
  dettagliHtml += "<tr><td colspan='2'><button class='btn btn-primary' id='salvaButton' onclick='salvaModificheCliente(" + clienteId + ")'>Salva</button> <button class='btn btn-primary' onclick='caricaTabellaClienti()'>Ritorna alla tabella clienti</button></td></tr>";
  dettagliHtml += "</table>";

  // Aggiorna il contenuto del container dei dettagli del cliente
  $("#tabellaContainer").html(dettagliHtml);
}

function salvaModificheCliente(clienteId) {
  // Recupera i valori modificati dai campi di input
  var nome = $("#nomeInput").val();
  var cognome = $("#cognomeInput").val();
  var indirizzo = $("#indirizzoInput").val();
  var cap = $("#capInput").val();
  var comune = $("#comuneInput").val();
  var provincia = $("#provinciaInput").val();
  var telefono = $("#telefonoInput").val();
  var email = $("#emailInput").val();

  // Costruisce l'oggetto cliente con i dati modificati
  var cliente = {
    id: clienteId,
    nome: nome,
    cognome: cognome,
    indirizzo: indirizzo,
    cap: cap,
    comune: comune,
    provincia: provincia,
    tel: telefono,
    mail: email
  };

  // Effettua una chiamata AJAX per salvare le modifiche del cliente
  $.ajax({
    url: "/api/modclienti/" + clienteId,
    method: "PUT",
    data: JSON.stringify(cliente),
    contentType: "application/json",  // confermo al server che sono dati json
    success: function(response) {
      // Visualizza un messaggio di successo in console
      console.log("Modifiche salvate con successo.");
      ripristinaStatoModifica(clienteId, cliente);
      mostraPopup("Cliente modificato con successo.");
    },
    error: function(error) {
      // Visualizza un messaggio di errore in console
      console.error("Si è verificato un errore durante il salvataggio delle modifiche.");
      mostraPopup("Non é stato possibile modificare il cliente");
    }
  });
}

<!-- Script per ripristinare lo stato -pronto per la modifica- -->
function ripristinaStatoModifica(clienteId, cliente) {
  // Ripristina la scritta del pulsante a "Modifica" e assegna la funzione di modifica
  $("#salvaButton").text("Modifica").attr("onclick", "modificaCliente(" + clienteId + "," + JSON.stringify(cliente) + ")");

  // Rendi nuovamente i campi di input non modificabili
  $("#nomeInput").prop("disabled", true);
  $("#cognomeInput").prop("disabled", true);
  $("#indirizzoInput").prop("disabled", true);
  $("#capInput").prop("disabled", true);
  $("#comuneInput").prop("disabled", true);
  $("#provinciaInput").prop("disabled", true);
  $("#telefonoInput").prop("disabled", true);
  $("#emailInput").prop("disabled", true);
}

<!-- Script per caricare dettagli clienti ed eventualmente modificarli -->
function mostraDettagliCliente(clienteId) {
  // Effettua una chiamata AJAX per ottenere i dettagli del cliente
  $.get("/api/clienti/" + clienteId, function(cliente) {
    // Costruisce la tabella dei dettagli del cliente
    var dettagliHtml = "<table class='table table-striped'>";
    dettagliHtml += "<tr><td><strong>Id:</strong></td><td>" + cliente.id + "</td></tr>";
    dettagliHtml += "<tr><td><strong>Nome:</strong></td><td>" + cliente.nome + "</td></tr>";
    dettagliHtml += "<tr><td><strong>Cognome:</strong></td><td>" + cliente.cognome + "</td></tr>";
    dettagliHtml += "<tr><td><strong>Via:</strong></td><td>" + cliente.indirizzo + "</td></tr>";
    dettagliHtml += "<tr><td><strong>CAP:</strong></td><td>" + cliente.cap + "</td></tr>";
    dettagliHtml += "<tr><td><strong>Comune:</strong></td><td>" + cliente.comune + "</td></tr>";
    dettagliHtml += "<tr><td><strong>Provincia:</strong></td><td>" + cliente.provincia + "</td></tr>";
    dettagliHtml += "<tr><td><strong>Telefono:</strong></td><td>" + cliente.tel + "</td></tr>";
    dettagliHtml += "<tr><td><strong>E-Mail:</strong></td><td>" + cliente.mail + "</td></tr>";

    // Aggiunge il pulsante di modifica dei dati
    dettagliHtml += "<tr><td colspan='2'><button class='btn btn-primary' onclick='modificaCliente(" + clienteId + ", " + JSON.stringify(cliente) + ")'>Modifica</button>  <button class='btn btn-primary' onclick='caricaTabellaClienti()'>Ritorna alla tabella clienti</button></td></tr>";
    dettagliHtml += "</table>";

    // Aggiorna il contenuto del container dei dettagli del cliente
    $("#tabellaContainer").html(dettagliHtml);
  });
}

<!-- Script jQuery per caricare la tabella assistenza -->
  function caricaTabellaAssistenza() {
  var assistenzaCorrente = {}; // Variabile per tenere traccia dell'assistenza corrente

  $.get("/api/assistenza", function(data) {
   var tabellaHtml = "<div class='text-center mt-3'><h4>Inserisci un nuovo centro assistenza</h4></div>" +
                         "<div class='row justify-content-center'>" +
                         "<div class='col-4'>" +
                         "<div class='input-group mb-3'>" +
                         "<input type='text' class='form-control' placeholder='Nome' id='nomeAssistenza'>" +
                         "</div>" +
                         "</div>" +
                         "<div class='col-4'>" +
                         "<div class='input-group mb-3'>" +
                         "<input type='text' class='form-control' placeholder='Indirizzo' id='indirizzoAssistenza'>" +
                         "</div>" +
                         "</div>" +
                         "<div class='col-4'>" +
                         "<div class='input-group mb-3'>" +
                         "<button onclick='salvaNuovaAssistenza()' class='btn btn-primary'>Salva</button>" +
                         "</div>" +
                         "</div>" +
                         "</div>" +
                         "<div class='text-center mt-3'><h4>Centri Assistenza presenti in archivio</h4></div>"+
                         "<table class='table table-striped table-bordered table-hover'>" +
                         "<thead><tr><th>Cod.</th><th>Nome</th><th>Indirizzo</th><th>Azioni</th></tr></thead>" +
                         "<tbody>";
    for (var i = 0; i < data.length; i++) {
      var assistenza = data[i];
      tabellaHtml += "<tr>";
      tabellaHtml += "<td>" + assistenza.id + "</td>";
      tabellaHtml += "<td><span contenteditable='false' class='editable'>" + assistenza.nome + "</span></td>";
      tabellaHtml += "<td><span contenteditable='false' class='editable'>" + assistenza.indirizzo + "</span></td>";
      tabellaHtml += "<td>";
      tabellaHtml += "<button onclick='modificaAssistenza(this)' class='btn btn-primary'><i class='fas fa-pen'></i> Modifica</button>";
      tabellaHtml += "<button onclick='eliminaAssistenza(this)' class='btn btn-danger'><i class='fas fa-trash'></i> Elimina</button>";
      tabellaHtml += "</td>";
      tabellaHtml += "</tr>";
      tabellaHtml += "</tr>";
    }

    tabellaHtml += "</tbody></table>";
    $("#tabellaContainer").html(tabellaHtml).hide().fadeIn("slow"); //carica tabella con effetto dissolvenza
  });
}

function modificaAssistenza(button) {
  var row = $(button).closest("tr");
  var cells = row.find(".editable");
  cells.attr("contenteditable", "true");

  var saveButton = $("<button>", {
    class: "btn btn-success",
    html: "<i class='fas fa-save'></i> Salva",
    click: function() {
      salvaModifiche(this);
    }
  });

  $(button).replaceWith(saveButton);
}

function salvaModifiche(button) {
  var row = $(button).closest("tr");
  var cells = row.find(".editable");
  cells.attr("contenteditable", "false");

  // Esegui solo se il pulsante è nello stato "Salva"
  if ($(button).hasClass("btn-success")) {
    $(button).text("Modifica").removeClass("btn-success").addClass("btn-primary");

    var assistenzaCorrente = {
      id: row.find("td:eq(0)").text(),
      nome: row.find("td:eq(1) span").text(),
      indirizzo: row.find("td:eq(2) span").text()
    };

    $.ajax({
      url: "/api/upassistenza/" + assistenzaCorrente.id,
      type: "PUT",
      contentType: "application/json",
      data: JSON.stringify(assistenzaCorrente),
      success: function(response) {
        // Aggiorna la tabella con i dati salvati
        row.find("td:eq(1) span").text(response.nome);
        row.find("td:eq(2) span").text(response.indirizzo);
        // Reimposta lo stato per consentire ulteriori modifiche
        var editButton = $("<button>", {
          class: "btn btn-primary",
          html: "<i class='fas fa-pen'></i> Modifica",
          click: function() {
            modificaAssistenza(this);
          }
        });
        $(button).replaceWith(editButton);

      },
      error: function(xhr, status, error) {
        // Gestisci eventuali errori nella chiamata REST
      }
    });
  }
}

function eliminaAssistenza(button) {
  var row = $(button).closest("tr");
  var assistenzaId = row.find("td:eq(0)").text();

  $.ajax({
    url: "/api/assistenza/" + assistenzaId,
    type: "DELETE",
    success: function(response) {
      // Rimuovi la riga dalla tabella
      row.remove();
      var eliminato = "Centro assistenza eliminato con successo";
      mostraPopup(eliminato);
    },
    error: function(xhr, status, error) {
      if (xhr.status === 409) {
        // Errore di conflitto - non è possibile cancellare il record
        var errorMessage = "Impossibile cancellare il centro assistenza, ci sono articoli collegati.";
        mostraPopup(errorMessage);
      } else {
        // Altro errore
        var errorMessage = "Si è verificato un errore durante la cancellazione del record.";
        mostraPopup(errorMessage);
      }
    }

  });
};

function mostraPopup(messaggio) {
  var modalHtml = '<div class="modal fade" id="errorModal" tabindex="-1" aria-labelledby="errorModalLabel" aria-hidden="true">' +
    '<div class="modal-dialog modal-dialog-centered">' +
    '<div class="modal-content">' +
    '<div class="modal-header">' +
    '<h5 class="modal-title" id="errorModalLabel">ATTENZIONE</h5>' +
    '<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>' +
    '</div>' +
    '<div class="modal-body">' +
    '<p>' + messaggio + '</p>' +
    '</div>' +
    '<div class="modal-footer">' +
    '<button type="button" class="btn btn-primary" data-bs-dismiss="modal">OK</button>' +
    '</div>' +
    '</div>' +
    '</div>' +
    '</div>';

  // Aggiungi il codice del popup al documento
  $(document.body).append(modalHtml);

  // Mostra il popup
  $('#errorModal').modal('show');

  // Rimuovi il popup dal documento quando viene chiuso
  $('#errorModal').on('hidden.bs.modal', function() {
    $(this).remove();
  });
}

function salvaNuovaAssistenza() {
  var nome = $("#nomeAssistenza").val();
  var indirizzo = $("#indirizzoAssistenza").val();

  var nuovaAssistenza = {
    nome: nome,
    indirizzo: indirizzo
  };

  $.ajax({
    url: "/api/assistenza",
    type: "POST",
    contentType: "application/json",
    data: JSON.stringify(nuovaAssistenza),
    success: function(response) {
      // Aggiungi la nuova riga alla tabella
      var newRow = "<tr>";
      newRow += "<td>" + response.id + "</td>";
      newRow += "<td><span contenteditable='false' class='editable'>" + response.nome + "</span></td>";
      newRow += "<td><span contenteditable='false' class='editable'>" + response.indirizzo + "</span></td>";
      newRow += "<td>";
      newRow += "<button onclick='modificaAssistenza(this)' class='btn btn-primary'><i class='fas fa-pen'></i> Modifica</button>";
      newRow += "<button onclick='eliminaAssistenza(this)' class='btn btn-danger'><i class='fas fa-trash'></i> Elimina</button>";
      newRow += "</td>";
      newRow += "</tr>";

      $("tbody").append(newRow);
        mostraPopup("Nuovo centro assistenza aggiunto con successo")
      // Resetta i campi del form
      $("#nomeAssistenza").val("");
      $("#indirizzoAssistenza").val("");
    },
    error: function(xhr, status, error) {
      // Gestisci eventuali errori nella chiamata REST
    }
  });
}



<!-- Script jQuery per caricare la tabella dei articoli -->
function caricaTabellaArticoli() {
    $.get("/api/clienti", function(data) {
        var tabellaHtml = "<table class='table table-striped table-bordered table-hover'>";
        tabellaHtml += "<thead><tr><th>Codice Articolo</th><th>Tipologia</th><th>Marca</th><th>Modello</th><th>Descrizione Guasto</th><th>Assistenza</th><th>Cliente</th></tr></thead>";
        tabellaHtml += "<tbody>";
        for (var i = 0; i < data.length; i++) {
            var cliente = data[i];
            for(var j = 0; j < cliente.articoli.length; j++) {
                var articolo = cliente.articoli[j];
                tabellaHtml += "<tr>";
                tabellaHtml += "<td>" + articolo.id + "</td>";
                tabellaHtml += "<td>" + articolo.tipologia.nome_tipologia + "</td>";
                tabellaHtml += "<td>" + articolo.marca + "</td>";
                tabellaHtml += "<td>" + articolo.modello + "</td>";
                tabellaHtml += "<td>" + articolo.tipo_guasto + "</td>";
                tabellaHtml += "<td>" + articolo.assistenza.nome + "</td>";
                tabellaHtml += "<td>" + cliente.nome + " " + cliente.cognome + "</td>";

                tabellaHtml += "</tr>";
            }
        }
        tabellaHtml += "</tbody></table>";
        $("#tabellaContainer").html(tabellaHtml).hide().fadeIn("slow"); //carica tabella con effetto dissolvenza
    });
}

