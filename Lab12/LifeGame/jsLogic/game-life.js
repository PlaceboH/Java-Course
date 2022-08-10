function step(state, siblings) {
    var counter = 0;
    for (var i = 0; i < siblings.length; i++) {
        if (siblings[i] === 1) {
            counter++;
        }
    }
    if (state === 1 && (counter === 2 || counter === 3)) {
        return 1;
    } else if (counter === 3) {
        return 1;
    }
    return 0;
}
