import shlex
import os

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


if __name__ == "__main__":
    get_tasks_form_cpuset()
