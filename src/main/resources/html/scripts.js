function fetch_twitter_data(arr) {
  for (var i = 0; i < arr.length; i++) {
    var newRow =
      `<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
        <div class="col p-4 d-flex flex-column position-static">
          <!-- category -->
          <strong class="d-inline-block mb-2 text-primary">Twitter</strong>
          <!-- title -->
          <h3 class="mb-0 limit-title">` +
      arr[i].title +
      `</h3>
          <!-- tonality -->
          <div class="mb-1 text-muted">` +
      arr[i].tonality +
      `</div>
          <!-- content -->
          <p class="card-text mb-auto limit-content">` +
      arr[i].content +
      `</p>
          <a href="` +
      arr[i].link +
      `" class="stretched-link" target="_blank">Continue reading</a>
        </div>
      </div>`;

    document.getElementById("twitter-col").innerHTML += newRow;
  }
}

function fetch_cnn_data(arr) {
  for (var i = 0; i < arr.length; i++) {
    var newRow =
      `<div class="row no-gutters border rounded overflow-hidden flex-md-row mb-4 shadow-sm h-md-250 position-relative">
          <div class="col p-4 d-flex flex-column position-static">
            <!-- category -->
            <strong class="d-inline-block mb-2 text-primary">CNN</strong>
            <!-- title -->
            <h3 class="mb-0 limit-title">` +
      arr[i].title +
      `</h3>
            <!-- tonality -->
            <div class="mb-1 text-muted">` +
      arr[i].tonality +
      `</div>
            <!-- content -->
            <p class="card-text mb-auto limit-content">` +
      arr[i].content +
      `</p>
            <a href="` +
      arr[i].link +
      `" class="stretched-link" target="_blank">Continue reading</a>
          </div>
        </div>`;

    document.getElementById("cnn-col").innerHTML += newRow;
  }
}
