# Добирает реги до нужного количества из сумки с регами.
# Количество по умолчанию можно менять в строках 29-36.

sub getRegs()
   UO.charprint(UO.GetSerial(), '172','Сумка откуда набираем реги')
   UO.exec('addobject SumkaRegov')
   while uo.targeting() 
      wait(100) 
   wend
   UO.charprint(UO.GetSerial(), '172','Куда складываем')
   UO.exec('addobject MyBag')
   while uo.targeting() 
      wait(100) 
   wend
   UO.useobject(UO.GetSerial('MyBag'))
   UO.useobject(UO.GetSerial('SumkaRegov'))
   VAR i
   DIM regs[8]
   regs[0] = '0x0F8D' ; Spider's Silk
   regs[1] = '0x0F85' ; Ginsengs
   regs[2] = '0x0F88' ; Nightshades
   regs[3] = '0x0F8C' ; Sulphurousashes
   regs[4] = '0x0F84' ; Garlics
   regs[5] = '0x0F7B' ; Blood Mosses
   regs[6] = '0x0F7A' ; Black Pearls
   regs[7] = '0x0F86' ; Mandrake Roots
   
   DIM regsCount[8]
   regsCount[0] = 40 ; Spider's Silk
   regsCount[1] = 40 ; Ginsengs
   regsCount[2] = 40 ; Nightshades
   regsCount[3] = 40 ; Sulphurousashes
   regsCount[4] = 40 ; Garlics
   regsCount[5] = 50 ; Blood Mosses
   regsCount[6] = 50 ; Black Pearls
   regsCount[7] = 50 ; Mandrake Roots
   
   UO.SetReceivingContainer(UO.GetSerial('SumkaRegov')) 
   for i = 0 to 7
      UO.findtype(regs[i],-1,UO.GetSerial('MyBag'))
      if uo.findcount() then
         UO.Grab('0', 'finditem')
         wait(10)
      end if
   next
   UO.SetReceivingContainer(UO.GetSerial('MyBag')) 
   for i = 0 to 7
      UO.findtype(regs[i],-1,UO.GetSerial('SumkaRegov'))
      if uo.findcount() then
         UO.MoveItem('finditem', regsCount[i], UO.GetSerial('MyBag'), '100', '100', '0')
         wait(10)
      end if
   next
   UO.UnsetReceivingContainer()
end sub