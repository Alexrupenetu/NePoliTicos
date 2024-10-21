module msd (
  input [4:0] i,
  output [3:0] o
);
    
assign o = (i<10) ? i[3:0] : (i>=10 && i<=19) ? 4'd1 : (i>=20 && i<=29) ? 4'd2 : 4'd3;

endmodule

module msd_tb;
  reg [4:0] i;
  wire [3:0] o;

  msd msd_i (.i(i), .o(o));

  integer k;
  initial begin
    $display("Time\ti\t\to");
    $monitor("%0t\t%b(%2d)\t%b(%0d)", $time, i, i, o, o);
    i = 0;
    for (k = 1; k < 32; k = k + 1)
      #10 i = k;
  end
endmodule