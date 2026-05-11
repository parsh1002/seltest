pipeline{
agent any
tools{
maven 'Maven'
}
stages{
stage('Checkout'){
steps{
git branch : 'main', url:'https://github.com/parsh1002/seltest.git'
}
}
stage('Build'){
steps{
sh 'mvn clean install'
}
}
stage('Test'){
steps{
sh 'mvn test'
}
}
stage('Run Application'){
steps{
sh 'mvn run'
}
}
}
post{
success{
echo 'succ'
}
failure{
echo 'fail'
}
}
}
