KERNEL_MODULE_AUTOLOAD += "${@bb.utils.contains('MACHINE_FEATURES', '1wire', 'w1-gpio w1-therm w1_therm', '', d)}"
KERNEL_MODULE_PROBECONF += "w1-gpio"
module_conf_w1-gpio = "options w1-gpio pullup=1"
