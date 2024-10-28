module cmp2b(
  input [1:0] a, b,
  output eq, it, gt
);

assign it = (~(a[1]) & b[1]) | (~(a[1]) & ~(a[0]) & b[0]) | (~(a[0]) & b[1] & b[0]);
assign eq = (~(a[1]) & ~(a[0]) & ~(b[1]) & ~(b[0])) | (~(a[1]) & a[0] & ~(b[1]) & b[0]) | (a[1] & a[0] & b[1] & b[0]) | (a[1] & ~(a[0]) & b[1] & ~(b[0]));
assign gt = (a[0] & ~(b[1]) & ~(b[0])) | (a[1] & a[0] & ~(b[0])) | (a[1] & ~(b[1]));

endmodule

module cmp2b_tb;
  reg [1:0] a, b;
  wire eq, it, gt;
  
  cmp2b cmp2b_i(
  .a(a),
  .b(b),
  .eq(eq),
  .it(it),
  .gt(gt)
  );
  
  integer i;
  
  initial begin
    {a, b} = 0;
    
    $display("Time\ta\tb\tit\teq\tgt\t");
    $monitor("%0t\t%b\t%b\t%b\t%b\t%b\t", $time, a, b, it, eq, gt);
    
    for(i=0; i<16; i=i+1) begin
      {a, b} = i;
      #10;
    end
  end
endmodule