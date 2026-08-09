from collections import defaultdict

def solution(record):
    answer = []
    users = defaultdict(str)

    for rec in record:
        splited_rec = rec.split()
        command = splited_rec[0]
        id = splited_rec[1]
        if (command == 'Enter' or command == 'Change'):
            users[id] = splited_rec[2]
    
    for rec in record:
        splited_rec = rec.split()
        command = splited_rec[0]
        id = splited_rec[1]
        if(command == 'Enter'):
            answer.append(users[id] + '님이 들어왔습니다.')
        if(command == 'Leave'):
            answer.append(users[id] + '님이 나갔습니다.')
    
    return answer