module cmp2b(
  input [1:0] a, b,
  output eq, it, gt
);

assign it = (~(a[1]) & b[1]) | (~(a[1]) & ~(a[0]) & b[0]) | (~(a[0]) & b[1] & b[0]);
assign eq = (~(a[1]) & ~(a[0]) & ~(b[1]) & ~(b[0])) | (~(a[1]) & a[0] & ~(b[1]) & b[0]) | (a[1] & a[0] & b[1] & b[0]) | (a[1] & ~(a[0]) & b[1] & ~(b[0]));
assign gt = (a[0] & ~(b[1]) & ~(b[0])) | (a[1] & a[0] & ~(b[0])) | (a[1] & ~(b[1]));

endmodule

module cmp4b(
  input [3:0] x, y,
  output iT, eQ, gT 
);

wire it1, it2, eq1, eq2, gt1, gt2;

cmp2b cmp1(
  .a(x[1:0]),
  .b(y[1:0]),
  .it(it1),
  .eq(eq1),
  .gt(gt1)
);

cmp2b cmp2(
  .a(x[3:2]),
  .b(y[3:2]),
  .it(it2),
  .eq(eq2),
  .gt(gt2)
);
 
assign iT = (it1 & eq2) | it2;
assign eQ = eq1 & eq2;
assign gT = (gt1 & eq2) + gt2;

endmodule

module cmp4b_tb;
  reg [3:0] x, y;
  wire iT, eQ, gT;
  
  cmp4b add4b_i(
  .x(x),
  .y(y),
  .iT(iT),
  .gT(gT),
  .eQ(eQ)
  );  
  
  integer i;
  
  initial begin 
    {x, y} = 0;
    
    $display("Time\tx\ty\tiT\teQ\tgT\t");
    $monitor("%0t\t%b\t%b\t%b\t%b\t%b\t", $time, x, y, iT, eQ, gT);
    for(i=0; i<256; i=i+1) begin
      {x, y} = i;
      #10;
    end
  end
endmodule