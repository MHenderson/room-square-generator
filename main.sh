#!/bin/bash
read -p "Size [12]: " size
size=${size:-12}
read -p "Iterations [400]: " its
its=${its:-400}
read -p "Restarts [30]: " restarts
restarts=${restarts:-30}
read -p "Random seed [42]: " seed
seed=${seed:-42}
java -Dfile.encoding=UTF-8 -jar out/artifacts/room_square_generator_jar/room-square-generator.jar $size $its $restarts $seed

