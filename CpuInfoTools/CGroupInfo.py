import shlex
import os
import subprocess

proc_path = '/proc'
cmd_get_all_proc = 'adb shell ls proc'
pid_list = []
fg_list = []
fg_ui_list = []
fg_boost_list = []
bg_list = []
bg_system_list = []
background = '/background'
foreground = '/foreground'
foreground_ui = '/foreground/ui'
foreground_boost = '/foreground/boost'
background_system = '/system-background'
default = '/'


list_cpuset_name = ['performance', 'foreground', 'foreground-ui', 'foreground-boost', 'background', 'background-system']

list_cpuset_path = ['/dev/cpuset/performance/tasks',
                    '/dev/cpuset/foreground/tasks',
                    '/dev/cpuset/foreground/ui/tasks',
                    '/dev/cpuset/foreground/boost/tasks',
                    '/dev/cpuset/background/tasks',
                    '/dev/cpuset/background-system/tasks',
                    '/dev/cpuset/tasks']

list_cpuset = []
list_cpuset_performance_task = []
list_cpuset_foreground_task = []
list_cpuset_foreground_ui_task = []
list_cpuset_foreground_boost_task = []
list_cpuset_background_task = []
list_cpuset_background_system_task = []
list_cpuset_default_task = []


def execute_command(cmdstring, list=None, cwd=None, timeout=None, shell=True):
    if shell:
        cmdstring_list = cmdstring
    else:
        cmdstring_list = shlex.split(cmdstring)

    sub = os.popen(cmdstring_list)
    return sub.readlines()


# 获取此cpuset中的所有 Task
def get_tasks_form_cpuset():
    list_cpuset_performance_task = execute_command("adb shell cat " + list_cpuset_path[0])
    list_cpuset_foreground_task = execute_command("adb shell cat " + list_cpuset_path[1])
    list_cpuset_foreground_ui_task = execute_command("adb shell cat " + list_cpuset_path[2])
    list_cpuset_foreground_boost_task = execute_command("adb shell cat " + list_cpuset_path[3])
    list_cpuset_background_task = execute_command("adb shell cat " + list_cpuset_path[4])
    list_cpuset_background_system_task = execute_command("adb shell cat " + list_cpuset_path[5])
    list_cpuset_default_task = execute_command("adb shell cat " + list_cpuset_path[6])

    print("------------------Begin performance_task info ---------------------")
    get_cpuset_info_from_list(list_cpuset_performance_task)
    print("------------------End performance_task info------------------------")

    print("--------------Begin foreground_boost_task info ------------------ ")
    get_cpuset_info_from_list(list_cpuset_foreground_boost_task)
    print("--------------End foreground_boost_task info----------------------------")

    print("---------------Begin foreground_ui_task info  ------------------- ")
    get_cpuset_info_from_list(list_cpuset_foreground_ui_task)
    print("---------------End foreground_ui_task info--------------------------")

    print("-----------------Begin foreground_task info ----------------------")
    get_cpuset_info_from_list(list_cpuset_foreground_task)
    print("-------------------End foreground_task info----------------------")

    print("-----------------Begin background_task info  ---------------------")
    get_cpuset_info_from_list(list_cpuset_background_task)
    print("-----------------End background_task info-------------------------")

    print("-------------Begin background_system_task info ---------------------")
    get_cpuset_info_from_list(list_cpuset_background_system_task)
    print("-------------End background_system_task info-----------------------------")

    print("-------------Begin list_cpuset_default_task info ---------------------")
    get_cpuset_info_from_list(list_cpuset_default_task)
    print("-------------End list_cpuset_default_task info-----------------------------")


def get_cpuset_info_from_list(task_list):
    ps_info = execute_command("adb shell ps")
    for task in task_list:
        for process in ps_info:
            if(task.strip() in process):
                print("Task " + task + " ProcessInfo = " + process)


# 获取cpuset对应的cpu信息
def get_cpus_for_cpuset():
    return

def get_cpuset_by_pid(pid):
    return 0


def get_cpuset_all():
    # 1. 获取当前所有进程的pid
    get_all_pid()

    # 2. 获取单个进程的 cpuset 信息
    get_cpuset_info(pid_list , foreground , 0)

    return 0


def get_all_pid():
    proc = subprocess.Popen([cmd_get_all_proc], stdout=subprocess.PIPE, shell=True, universal_newlines=True)
    (out, err) = proc.communicate()

    for line in out.splitlines():
        if line.isdigit():
            pid_list.append(line)
            # for i in pid_list:
            #     print(i)


def get_cpuset_info(lists , cpuset , pid_number_min):
    for pid in lists:
        proc1 = subprocess.Popen(['adb shell cat /proc/' + pid + "/cpuset"], stdout=subprocess.PIPE, shell=True,
                                 universal_newlines=True)
        (out1, err) = proc1.communicate()
        position = out1.strip()
        # print(position)
        if position == cpuset and int(pid) >= pid_number_min:
            proc2 = subprocess.Popen(['adb shell cat /proc/' + pid + "/comm"], stdout=subprocess.PIPE, shell=True,
                                     universal_newlines=True)
            (out2, err) = proc2.communicate()
            print("-----------------------------")
            print("pid       = " + pid.strip())
            print("cpuset    = " + out1.strip())
            print("proc name = " + out2.strip())
            print("-----------------------------")


def get_pid_form_cpuset(cpuset_info):
    cmd = ''
    if cpuset_info == 'fg':
        cmd = 'adb shell cat /dev/cpuset/foreground/tasks'
    if cpuset_info == 'system-bg':
        cmd = 'adb shell cat /dev/cpuset/system-background/tasks'
    if cpuset_info == 'bg':
        cmd = 'adb shell cat /dev/cpuset/background/tasks'
    if cpuset_info == 'df':
        cmd = 'adb shell cat /dev/cpuset/tasks'
    if cpuset_info == 'fg-ui':
        cmd = 'adb shell cat /dev/cpuset/foreground/ui/tasks'

    proc = subprocess.Popen([cmd], stdout=subprocess.PIPE, shell=True,
                            universal_newlines=True)
    (out, err) = proc.communicate()
    print(cpuset_info + " ******************* ")
    print(cpuset_info + " proc = " + out)
    print(cpuset_info + " ******************* ")

    for line in out.splitlines():
        if line.isdigit():
            pid_list.append(line)
            for i in pid_list:
                print(i)


if __name__ == "__main__":
    get_tasks_form_cpuset()
    #get_cpuset_all()
    # get_pid_form_cpuset('fg')
    # get_pid_form_cpuset('system-bg')
    # get_pid_form_cpuset('bg')
    # get_pid_form_cpuset('df')
    # get_pid_form_cpuset('fg-ui')
