# Concurrency Downloader

This project collects the exercises from a week focused on Java concurrency:

- Raw threads vs `ExecutorService`
- Thread pools and benchmarking
- `Callable`, `Future`, and exception handling
- Race conditions and synchronization
- `volatile` keyword and thread-safe logging
- `CountDownLatch` and timestamps

## Modules

- `DownloadTask`
    - Uses `ExecutorService`.
    - Submits 10 download tasks implemented as `Callable<String>`.
    - Measures total execution time and prints results like "File X downloaded successfully".

- `Counter`, `RaceConditionDemo`
    - `RaceConditionDemo` shows a data race on a shared counter with synchronization.

- `Worker`, `VolatileDemo`
    - `Worker` runs a loop controlled by a `volatile boolean running`.
    - `VolatileDemo` starts the worker, waits a bit, then calls `stop()` and joins the thread.
    - Logging is done through a `synchronized` `log` method to avoid mixed output.

- `LatchDownloadDemo`
    - Runs several "download" tasks in parallel using a fixed thread pool.
    - Uses a `CountDownLatch` to wait for all tasks to finish.
    - Logs per-task start/end timestamps and prints all `Future<String>` results.

## How to run

- Run `DownloadTask` to see thread pool benchmarks.
- Run `RaceConditionDemo` to observe a fixed race condition with `synchronized`.
- Run `VolatileDemo` for `volatile` + logging.
- Run `LatchDownloadDemo` for `CountDownLatch` + timestamps.
