declare dc_infra=docker-compose.yml
declare dc_app=docker-compose-app.yml
export DOCKER_USERNAME="ghirmai"
export DOCKER_ACCESS_TOKEN="dckr_pat_1RlBdo7la9u8W3wuBUlAZG_v0Q8"



function build_api() {
    cd bookmarker-api
    mvn compile jib:build
    cd ..
}

function start_infra() {
   echo "Starting infra docker containers....."
   docker-compose -f ${dc_infra} up -d
   docker-compose -f ${dc_infra} logs -f

}
function stop_infra() {
   echo "stopping infra docker containers....."
   docker-compose -f ${dc_infra} stop
   docker-compose -f ${dc_infra} rm -f

}
function start() {
   build_api
   echo "starting all docker containers....."
   docker-compose -f ${dc_infra} -f ${dc_app} up  -d
   docker-compose -f ${dc_infra} -f ${dc_app} logs -f



}
function stop() {
  echo "Stopping all docker containers...."
  docker-compose -f ${dc_infra} -f ${dc_app} stop
  docker-compose -f ${dc_infra} -f ${dc_app} rm -f


}
function restart() {
  stop
  sleep 3
  start

}

action="start"

if [[ "$#" != "0" ]]
then
    action=$@
fi
eval ${action}