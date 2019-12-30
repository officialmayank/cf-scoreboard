# Scoreboard Application

## Build and Test
`sh gradlew build`

## Start Server
`java -jar build/libs/gs-rest-service-0.1.0.jar`

## Some Points
- Soft deleting the team by making it inactive
- A member can exist without a team or an inactive team but can score only in active team
- We can get total score by summing scores of team member but we are maintaining that value in team to fetch  it fast

## Improvements
- Add RequestValidator Layer
- Add Validations at model level
- Implement Response format for exceptions with correct httpStatus
- need to add record (id) not found exception everywhere (team not found, member not found)
