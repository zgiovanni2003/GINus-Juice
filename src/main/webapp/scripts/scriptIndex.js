

// Funzione per il carosello di immagini
function startImageCarousel() {
    const carouselContainer = document.querySelector('.carousel-container');
    const images = document.querySelectorAll('#image-carousel img');
    const totalImages = images.length;
    const slideWidth = images[0].clientWidth; // Larghezza di ogni immagine (assume immagini di larghezza uniforme)
    let currentIndex = 0; // Partiamo dalla prima immagine originale

    // Clona tutte le immagini e aggiungile al container
    images.forEach(img => {
        const clone = img.cloneNode(true);
        carouselContainer.appendChild(clone);
    });

    // Imposta la larghezza del container per accogliere tutte le immagini clonate
    const newTotalImages = totalImages * 2; // Raddoppia il numero di immagini per il loop infinito
    carouselContainer.style.width = `${newTotalImages * slideWidth}px`;

    // Avvia il carosello
    setInterval(() => {
        currentIndex++;

        // Applica la transizione per lo spostamento fluido tra le immagini
        carouselContainer.style.transition = 'transform 0.5s ease';
        carouselContainer.style.transform = `translateX(-${currentIndex * slideWidth}px)`;

        // Quando raggiungiamo la fine delle immagini originali, riportiamo alla prima immagine senza transizione
        if (currentIndex >= totalImages) {
            setTimeout(() => {
                carouselContainer.style.transition = 'none';
                currentIndex = 0;
                carouselContainer.style.transform = `translateX(-${currentIndex * slideWidth}px)`;
            }, 500); // Tempo che corrisponde alla durata della transizione CSS
        }
    }, 3000); // Cambia immagine ogni 3 secondi (puoi regolare il valore come preferisci)
}



// Esegui la funzione di controllo età quando la pagina si carica
window.onload = function() {
    var overlay = document.getElementById("overlay");
    var ageConfirmation = document.getElementById("age-confirmation");

    // Mostra l'overlay e il popup di conferma età
    overlay.style.display = "flex";
    ageConfirmation.style.display = "block";

    // Centra il popup verticalmente
    var overlayHeight = overlay.offsetHeight;
    var ageConfirmationHeight = ageConfirmation.offsetHeight;
    ageConfirmation.style.top = `${(overlayHeight - ageConfirmationHeight) / 2}px`;

    // Aggiungi la classe blur per applicare lo sfondo sfocato
    document.body.classList.add("blur");

    // Avvia il carosello di immagini
    startImageCarousel();
};

// Funzione per chiudere la barra gialla di informazione sulla spedizione
function closeShippingInfo() {
    document.getElementById("shipping-info").style.display = "none";
}

// Funzione per controllare l'età
function checkAge(isAdult) {
    var overlay = document.getElementById("overlay");
    var ageConfirmation = document.getElementById("age-confirmation");
    var body = document.querySelector("body");

    if (isAdult) {
        // Se l'utente è maggiorenne, rimuovi l'overlay e l'effetto di sfocatura
        overlay.style.display = "none";
        ageConfirmation.style.display = "none";
        body.classList.remove("blur");

        // Abilita tutti i link e i pulsanti della pagina
        var disabledElements = document.querySelectorAll(".disabled");
        disabledElements.forEach(function(element) {
            element.classList.remove("disabled");
            element.removeAttribute("disabled");
        });
    } else {
        // Se l'utente non è maggiorenne, mostra l'overlay e disabilita i link e i pulsanti della pagina
        overlay.style.display = "flex";
        ageConfirmation.style.display = "block"; // Assicura che l'elemento sia visibile

        // Aggiungi classe di sfocatura al body
        body.classList.add("blur");

        // Disabilita tutti i link e i pulsanti della pagina
        var interactiveElements = document.querySelectorAll("a, button");
        interactiveElements.forEach(function(element) {
            element.classList.add("disabled");
            element.setAttribute("disabled", true);
        });
    }
}

// Funzione per reindirizzare a coca-cola.it
function redirectToCocaCola() {
    window.location.href = "https://www.coca-cola.it";
}
