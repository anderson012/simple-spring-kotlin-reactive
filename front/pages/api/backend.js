export const saveDefaultItems = async () => {
    return fetch("http://localhost:8080/insert").then(response => response.text())
}

// fetch heroes from server
export const fetchHeroes = async () => {
    return fetch("http://localhost:8080/heroes").then(response => response.json())
}

/**
 * @param {VoidFunction | null} onmessage
 */
export const streamHeroesFromServer = (onmessage) => {
    return new Promise((resolve, reject) => {
        const eventSource = new EventSource("http://localhost:8080/heroes");
        eventSource.onmessage = (event) => {
            onmessage?.(JSON.parse(event.data));
        };

        eventSource.onerror = (event) => {
            if (eventSource.readyState === EventSource.CONNECTING) {
                eventSource.close();
                resolve(event);
            } else {
                reject(event);
                eventSource.close();
            }

            console.log(event);
        }
    })
}