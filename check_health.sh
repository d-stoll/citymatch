#!/usr/bin/env bash

echo "============ Testing availability of backend services ============"

if curl -s http://localhost:8099/actuator/health | grep '{"status":"UP"}' > /dev/null; then
  echo -e "[ \e[1;32mok\e[0m ] Gateway reachable & healthy"
else
  echo -e "[ \e[1;31mfailed\e[0m ] Gateway unreachable"
fi

if curl -s http://localhost:8099/tours/actuator/health | grep '{"status":"UP"}'  > /dev/null; then
  echo -e "[ \e[1;32mok\e[0m ] Tour service reachable & healthy"
else
  echo -e "[ \e[1;31mfailed\e[0m ] Tour service unreachable"
fi

if curl -s http://localhost:8099/questions/actuator/health | grep '{"status":"UP"}' > /dev/null; then
  echo -e "[ \e[1;32mok\e[0m ] Question service reachable & healthy"
else
  echo -e "[ \e[1;31mfailed\e[0m ] Question service unreachable"
fi

if curl -s http://localhost:8099/places/actuator/health | grep '{"status":"UP"}' > /dev/null; then
  echo -e "[ \e[1;32mok\e[0m ] Place service reachable & healthy"
else
  echo -e "[ \e[1;31mfailed\e[0m ] Place service unreachable"
fi

if curl -s http://localhost:8099/cities/actuator/health | grep '{"status":"UP"}' > /dev/null; then
  echo -e "[ \e[1;32mok\e[0m ] City service reachable & healthy"
else
  echo -e "[ \e[1;31mfailed\e[0m ] City service unreachable"
fi
